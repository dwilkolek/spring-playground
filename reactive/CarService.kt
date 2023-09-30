package eu.wilkolek.webflux.car

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class CarService(private val repository: CarRepository) {


    fun getOne(id: UUID): Mono<Car> {
        return repository.findById(id)
    }


    fun getAll(): Flux<Car> {
        return repository.findAll()
    }


    fun create(name: String): Mono<Car> {
        return repository.save(Car(name))
    }


    fun makeOlder(car: Car): Mono<Car> {
        car.age++
        return repository.save(car)
    }

}