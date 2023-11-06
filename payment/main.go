package main

import (
	"HMS/payment/routers"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()

	// r.Use(initMiddleware1)  global middleware
	routers.AdminRoutersInit(r)
	// config.Section("mysql").Key("app_name").SetValue("golangahua")
	// r.Run() // listen and serve on 0.0.0.0:8080
	r.Run(":9091")
}
