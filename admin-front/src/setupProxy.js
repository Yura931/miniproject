const {createProxyMiddleware} = require('http-proxy-middleware');
module.exports = function(app) {
    app.use(
        '/auth-service',
        createProxyMiddleware({
            target: 'http://localhost:8080',
            changeOrigin: true,
            pathRewrite: {

            }
        })
    );

    app.use(
        '/board-service',
        createProxyMiddleware({
            target: 'http://localhost:8000',
            changeOrigin: true,
        })
    );

};
