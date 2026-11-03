package id.ac.itera.pesanlapangan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform