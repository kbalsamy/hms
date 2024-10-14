package routers

import (
	"HMS/payment/controllers/admin"
	"HMS/payment/middlewares"

	"github.com/gin-gonic/gin"
)

func AdminRoutersInit(r *gin.Engine) {
	adminRouters := r.Group("/", middlewares.ParseJWT)
	{
		adminRouters.GET("/pay", admin.BasePaymentControl{}.Pay)
	}
}
