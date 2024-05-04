package com.harana.search.platform.home

import com.harana.search.platform.Circuit.zoomTo
import com.harana.web.actions.Init
import diode._

class HomeHandler extends ActionHandler(zoomTo(_.homeState)) {
  override def handle = {

    case Init(preferences) =>
      noChange
  }
}