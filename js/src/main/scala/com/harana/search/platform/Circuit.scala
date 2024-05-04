package com.harana.search.platform

import com.harana.search.platform.home.{HomeHandler, HomeStore}
import com.harana.web.base.BaseCircuit

case class State(homeState: HomeStore.State)

object Circuit extends BaseCircuit[State] {

  lazy val initialModel =
    State(
      HomeStore.initialState,
    )

  lazy val handlers =
    List(
      new HomeHandler,
    )
}