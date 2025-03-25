package config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/main/resources/config.properties"
})
public interface ConfigProperties extends Config {

    @Key("baseUrl")
    @DefaultValue("https://reqres.in/api")
    String baseUrl();

    @Key("token")
    String token();
}
