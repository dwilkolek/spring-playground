package eu.wilkolek.webflux.car

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CarRepository : R2dbcRepository<Car, UUID>
