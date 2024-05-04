package com.harana.search.platform

import com.harana.search.platform.home.ui.HomePanel
import com.harana.web.actions.Init
import com.harana.web.base.Analytics
import com.harana.web.external.helmet.Helmet
import com.harana.web.external.router.{Route, Switch, Router => ReactRouter}
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useEffect
import slinky.core.{CustomAttribute, FunctionalComponent}
import slinky.web.html._

import java.util.concurrent.atomic.AtomicReference

@react
object Router {

  type Props = Unit
  private val browserHistory = new Analytics().history(false)
  private val didInit = new AtomicReference[Boolean](false)

  val component = FunctionalComponent[Unit] { _ =>
    Circuit.diodeContext.Provider(Circuit) {

      useEffect(() => {
        if (!didInit.get) {
          Circuit.dispatch(Init(Map()))
          didInit.set(true)
        }
      })

      ReactRouter(history = browserHistory)(
        div(
          Helmet(
            meta(new CustomAttribute[String]("charSet") := "utf-8"),
            meta(name := "viewport", content := "width := device-width, initial-scale=1, shrink-to-fit=no"),
            meta(name := "theme-color", content := "#000000"),
            link(rel := "shortcut icon", href := "/favicon.ico"),
          ),
          h1("Above route"),
          Switch(
            Route("/", HomePanel.component, exact = true),
            Route("*", HomePanel.component)
          )
        )
      )
    }
  }
}