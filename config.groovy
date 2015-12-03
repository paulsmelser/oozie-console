environments {
    dev {
        spring.profiles.active = 'dev'
    }

    test {
        spring.profiles.active = 'test'
    }
    prod {
        spring.profiles.active = 'prod'
    }
}