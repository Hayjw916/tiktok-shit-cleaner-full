package io.hayjw916.tiktokshitcleaner.server.scripts

/**The whole point of this file is to be able to create a unique application.properties file for each operating system
 *
 * It gets your home computer name, as well as your operating system in order to correctly place the files
 *
 * this script exists soley because I hate having to redo the application.properties file everytime I switch operating systems*/
class CreateApplicationProperties {
    static void main(String[] args) {

        // First gets the OS name in order to get correct slashes
        def os = System.getProperty("os.name").toLowerCase()
        def userHome = System.getProperty("user.home")
        def fileSize = "10MB"
        def String fileSlash

        switch (os) {
            case 'windows 10':
                fileSlash = "\\"
                break
            case 'mac os x':
                fileSlash = "/"
                break
            case 'linux':
                fileSlash = "/"
                break
            default:
                println(os)
                break
        }

        File file = new File("src" + fileSlash + "main" + fileSlash + "resources" + fileSlash + "application.properties")
        def writer = new BufferedWriter(new FileWriter(file, false));
        def songPath = userHome + fileSlash + "tsc" + fileSlash + "songs"
        def submissionPath = userHome + fileSlash + "tsc" + fileSlash + "submissions"

        writer.writeLine("song.path=" + songPath)
        writer.writeLine("submission.path=" + submissionPath)

        writer.writeLine("spring.servlet.multipart.max-file-size=" + fileSize)
        writer.writeLine("spring.servlet.multipart.max-request-size=" + fileSize)

        writer.close()


    }
}
