package eu.wilkolek.webflux.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/hello")
class HelloController {


    @GetMapping("/{name}")
    fun hello(@PathVariable name: String?): Mono<String>? {
        return Mono.just("Hello $name")
    }
}