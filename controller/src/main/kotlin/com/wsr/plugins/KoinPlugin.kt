package com.wsr.plugins

import com.wsr.comet.CometRepository
import com.wsr.comet.CreateCometUseCase
import com.wsr.comet.FakeCometRepositoryImpl
import com.wsr.comet.GetCometsUseCase
import com.wsr.comet.GetOwnedCometsUseCase
import com.wsr.user.CreateUserUseCase
import com.wsr.user.FakeUserRepositoryImpl
import com.wsr.user.GetUserUseCase
import com.wsr.user.UserRepository
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoinPlugin() {
    val usecase =
        module {
            /*** Comet ***/
            factory<GetCometsUseCase> { GetCometsUseCase(get(), get()) }
            factory<GetOwnedCometsUseCase> { GetOwnedCometsUseCase(get(), get()) }

            factory<CreateCometUseCase> { CreateCometUseCase(get()) }

            /*** User ***/
            factory<GetUserUseCase> { GetUserUseCase(get()) }

            factory<CreateUserUseCase> { CreateUserUseCase(get()) }
        }
    val repository =
        module {
            /*** Comet ***/
            single<CometRepository> { FakeCometRepositoryImpl() }

            /*** User ***/
            single<UserRepository> { FakeUserRepositoryImpl() }
        }

    install(Koin) { modules(usecase + repository) }
}
