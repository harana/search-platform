package com.harana.search.platform

import com.harana.modules.Layers
import com.harana.modules.core.app.{App => CoreApp}
import com.harana.modules.vertx.Vertx
import com.harana.modules.vertx.models.RouteHandler._
import com.harana.modules.vertx.models.{Response, Route}
import io.vertx.core.http.HttpMethod._
import zio.{Task, UIO, ZIO}
import zio.managed.ZManagedZIOSyntax

object App extends CoreApp {

  private def routes = List(
    Route("/",                                      GET,    Standard(rc => ZIO.attempt(Response.Template("public/index.hbs"))), secured = true),
  )

  def startup: Task[Unit] =
    for {
      _                     <- Vertx.startHttpServer(
                                s"search.harana.build",
                                None,
                                routes,
                                authTypes = List(),
                                additionalAllowedHeaders = Set("jwt"),
                               ).provideLayer(Layers.vertx).toManaged.useForever

    } yield ()


  def shutdown: UIO[Unit] = {
    for {
      _                     <- Vertx.close.ignore.provideLayer(Layers.vertx)
    } yield ()
  }
}
