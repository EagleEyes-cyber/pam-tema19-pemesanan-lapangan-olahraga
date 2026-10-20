package id.ac.itera.pesanlapangan

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Tema: Aplikasi Pesan Lapangan\nBerjalan di Platform: ${platform.name}"
    }
}