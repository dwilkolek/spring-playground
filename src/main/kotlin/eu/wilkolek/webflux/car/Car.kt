package eu.wilkolek.webflux.car

import org.springframework.data.annotation.Id
import java.util.UUID


data class Car(var name: String, var age: Int = 0) {
    @Id
    var id: UUID? = null
}