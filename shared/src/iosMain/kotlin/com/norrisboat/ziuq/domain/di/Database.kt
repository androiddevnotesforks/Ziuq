package com.norrisboat.ziuq.domain.di

import com.norrisboat.ziuq.domain.database.DatabaseDriverFactory
import org.koin.dsl.module

actual fun databaseModule() = module {
    single { DatabaseDriverFactory() }
//    single { BankDao(get()) }
}