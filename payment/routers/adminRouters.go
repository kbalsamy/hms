package routers

import (
	"HMS/payment/controllers/admin"
	"HMS/payment/middlewares"
	"fmt"
	"time"

	"github.com/gin-gonic/gin"
)

func initMiddleware1(ctx *gin.Context) {
	start := time.Now().UnixNano()
	fmt.Println("midware1")

	ctx.Next()
	// ctx.Abort()

	fmt.Println("midware2")
	end := time.Now().UnixNano()
	fmt.Println(end - start)
}
func initMiddleware2(ctx *gin.Context) {
	start := time.Now().UnixNano()
	fmt.Println("midware1")

	ctx.Next()
	// ctx.Abort()

	fmt.Println("midware2")
	end := time.Now().UnixNano()
	fmt.Println(end - start)
}

func AdminRoutersInit(r *gin.Engine) {
	adminRouters := r.Group("/admin", middlewares.JWT)
	// r.Use(initMiddleware1)  //config
	{
		adminRouters.GET("/", admin.IndexController{}.Index)
		adminRouters.GET("/nav", admin.NavController{}.Index)
		adminRouters.GET("/bank", admin.BankController{}.Index)
		adminRouters.GET("/user", initMiddleware2, admin.UserController{}.Index)
		adminRouters.GET("/user/add", admin.UserController{}.Add)
		adminRouters.GET("/user/edit", admin.UserController{}.Edit)
	}
}
