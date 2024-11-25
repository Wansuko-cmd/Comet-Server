package com.wsr.plugins

import com.wsr.comet.*
import com.wsr.user.CreateUserUseCase
import com.wsr.user.FakeUserRepositoryImpl
import com.wsr.user.UserRepository
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
    val repository = module {
        /*** Comet ***/
        single<CometRepository> { FakeCometRepositoryImpl() }

        /*** User ***/
        single<UserRepository> { FakeUserRepositoryImpl() }
    }

    install(Koin) { modules(usecase + repository) }
}
