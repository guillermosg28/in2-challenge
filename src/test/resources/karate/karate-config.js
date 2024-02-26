function fn() {
    var env = karate.env;
    var port = karate.properties['base_url'].match(/:(\d+)/)[1];
    var config = {
        baseUrl: 'http://localhost:' + port
    };
    return config;
}
