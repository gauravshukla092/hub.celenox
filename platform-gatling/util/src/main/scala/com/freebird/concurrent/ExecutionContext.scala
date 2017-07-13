package com.freebird.concurrent

import java.util.concurrent.Executors

object ExecutionContext {


  ///TODO should be configurable
  val CONCURRENCY_FACTOR = 3

  object IO {
    implicit lazy val dbOperations: concurrent.ExecutionContext = concurrent.ExecutionContext.fromExecutor(
      Executors.newFixedThreadPool(Runtime.getRuntime.availableProcessors() * CONCURRENCY_FACTOR)
    )
  }

}
