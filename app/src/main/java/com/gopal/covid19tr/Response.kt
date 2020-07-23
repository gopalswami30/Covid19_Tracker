package com.gopal.covid19tr


/*
"statewise": [
		{
			"active": "39658",
			"confirmed": "59203",
			"deaths": "1980",
			"deltaconfirmed": "2852",
			"deltadeaths": "91",
			"deltarecovered": "785",
			"lastupdatedtime": "08/05/2020 21:33:07",
			"recovered": "17561",
			"state": "Total",
			"statecode": "TT",
			"statenotes": ""
		}
*/
data  class Response(val statewise:List<statewiseItem?>)
data class statewiseItem(
    val active:String?=null,
    val confirmed:String?=null,
    val deaths:String?=null,
    val deltaconfirmed:String?=null,
    val   deltadeaths:String?=null,
    val deltarecovered:String?=null,
    val  lastupdatedtime:String?=null,
    val recovered:String?=null,
    val state:String?=null,
    val  statecode:String?=null,
    val statenotes:String?=null

)