package eu.wilkolek.webflux

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID


@RestController
@RequestMapping("/api/cars")
class CarsController(private val carsRepository: CarRepository) {


    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): Mono<Car> {
        return carsRepository.findById(id)
    }

    @GetMapping("")
    fun getAll(): Flux<Car> {
        return carsRepository.findAll()
    }

    @PostMapping
    fun createNew(@RequestBody data: NewCarRequest): Mono<Car> {
        return carsRepository.save(Car(name = data.name))
    }

    @PatchMapping("/{id}/make-older")
    @Transactional
    fun makeOlder(@PathVariable id: UUID): Mono<Car> {
        return carsRepository.findById(id).flatMap {
            it.age++
           carsRepository.save(it)
        }
    }

    data class NewCarRequest(val name: String)
}

