tasks {
    register("installGitHooks", Copy::class) {
        group = "verification"

        from("$rootDir/local/git-hooks")
        into("$rootDir/.git/hooks")

        onlyIf {
            val fileToCheck = file("$rootDir/.git/hooks/pre-commit")
            !fileToCheck.exists()
        }
    }
}
