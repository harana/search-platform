package com.harana.search.platform.home

object HomeStore {

  case class State(loggedIn: Boolean)

  val initialState = State(false)
}