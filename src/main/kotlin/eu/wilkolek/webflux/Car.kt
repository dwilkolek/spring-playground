package eu.wilkolek.webflux

import org.springframework.data.annotation.Id
import java.util.UUID


open class Car(var name: String, var age: Int = 0) {
    @Id
    var id: UUID? = null
}