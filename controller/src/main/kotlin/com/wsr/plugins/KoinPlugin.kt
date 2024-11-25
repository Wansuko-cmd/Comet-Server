package com.wsr.plugins

import com.wsr.comet.CreateCometUseCase
import com.wsr.comet.GetCometsUseCase
import com.wsr.comet.GetOwnedCometsUseCase
import com.wsr.user.CreateUserUseCase
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoinPlugin() {
    val usecase = module {
        /*** Comet ***/
        factory<GetCometsUseCase> { GetCometsUseCase(get(), get()) }
        factory<GetOwnedCometsUseCase> { GetOwnedCometsUseCase(get(), get()) }

        factory<CreateCometUseCase> { CreateCometUseCase(get()) }

        /*** User ***/
        factory<CreateUserUseCase> { CreateUserUseCase(get()) }
    }
    val repository = module {}

    install(Koin) { modules(usecase + repository) }
}
