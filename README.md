## Definisi Tugas
Tugas ini dibuat untuk memenuhi kewajiban sebagai mahasiswa pengembangan aplikasi mobile (PAM) RB.

## Tema Proyek

| Komponen | Keterangan |
|---|---|
| Nomor tema | 19 |
| Tema | Pemesanan lapangan olahraga |
| Fitur platform | Notifikasi |
| Ketua kelompok | 124140198 |

## Anggota Kelompok
| No | Nama | NIM | Peran |
|---|---|---|---|
| 1 | Bimo Auliano | 124140198 | Ketua |
| 2 | Jova Pratama Nihanda | 124140019 | Anggota |
| 3 | Muhammad Faza Prasetyo | 124140204 | Anggota |

---

## Daftar Modul

- **[Modul 1](./Modul%201)**: Inisialisasi proyek Kotlin Multiplatform (`id.ac.itera.pesanlapangan`) untuk tema Pemesanan Lapangan Olahraga.
- **[Modul 2](./Modul%202)**: Model Data (`Lapangan`, `Pemesanan`, `StatusPemesanan`, `PemesananExt`), Coroutines, dan Flow (`Sumber<T>`, `SumberPemesananPalsu`, `MuatAman`, `DemoStatus`).

---

## Checkpoint Minggu 2 (Modul 2)

### Pembagian Tugas
| No | Nama | NIM | Mengerjakan | Berkas Terkait (`Modul 2/shared/...`) |
|---|---|---|---|---|
| 1 | Bimo Auliano | 124140198 | **Model Data Tema** (`data class` entitas utama & pendukung, `sealed class` status, *null safety*, dan *extension function*) | `model/Lapangan.kt`, `model/Pemesanan.kt`, `model/StatusPemesanan.kt`, `model/PemesananExt.kt` |
| 2 | Jova Pratama Nihanda | 124140019 | **Sumber Data Palsu** (`interface Sumber<T>`, `suspend` function dengan `delay`, `withContext(Dispatchers.IO)`, `Flow` perubahan status, serta penanganan error `muatAman`) | `data/Sumber.kt`, `data/SumberPemesananPalsu.kt`, `data/MuatAman.kt` |
| 3 | Muhammad Faza Prasetyo | 124140204 | **Bukti Berjalan** (`demoStatus` dengan `collect`, unit test pengujian `Flow` & `async`/`await`, serta dokumentasi keluaran konsol di `README.md`) | `data/DemoStatus.kt`, `App.kt`, `commonTest/.../SumberPemesananPalsuTest.kt`, `README.md` |

### Contoh Keluaran Konsol (`Flow.collect`)
```text
Sebelum collect
Menunggu petugas
Ditangani Pak Joko (Admin GOR)
Selesai: Lapangan selesai digunakan dengan tertib
Sesudah collect
```
*(Detail lengkap dan cara menjalankan pengujian terdapat pada [`Modul 2/README.md`](./Modul%202/README.md)).*
