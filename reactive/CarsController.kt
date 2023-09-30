package eu.wilkolek.webflux.car

import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.UUID


@RestController
@RequestMapping("/api/cars")
class CarsController(private val service: CarService) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): Mono<Car> {
        return service.getOne(id)
    }

    @GetMapping("")
    fun getAll(): Flux<Car> {
        return service.getAll()
    }

    @PostMapping
    fun createNew(@RequestBody data: NewCarRequest): Mono<Car> {
        return service.create(data.name)
    }

    @PatchMapping("/{id}/make-older")
    @Transactional
    fun makeOlder(@PathVariable id: UUID): Mono<ResponseEntity<Car>> {
        return service.getOne(id)
            .flatMap(service::makeOlder)
            .delayElement(Duration.ofSeconds(10))
            .map{ ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }

    data class NewCarRequest(val name: String)
}

