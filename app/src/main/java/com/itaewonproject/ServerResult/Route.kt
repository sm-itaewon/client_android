package com.itaewonproject.ServerResult

import java.util.*

class Route(var title:String,var location:String,var RouteID:Int,var updated:String,var childRoute:ArrayList<Route>)
{
    var opened=false
}
