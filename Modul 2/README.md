## Pembagian Tugas
| No | Nama | NIM | Mengerjakan | Berkas Terkait |
|---|---|---|---|---|
| 1 | Bimo Auliano | 124140198 | **Model Data Tema** (`data class` entitas utama & pendukung, `sealed class` status, *null safety*, dan *extension function*) | `model/Lapangan.kt`, `model/Pemesanan.kt`, `model/StatusPemesanan.kt`, `model/PemesananExt.kt` |
| 2 | Jova Pratama Nihanda | 124140019 | **Sumber Data Palsu** (`interface Sumber<T>`, `suspend` function dengan `delay`, `withContext(Dispatchers.IO)`, `Flow` perubahan status, serta penanganan error `muatAman`) | `data/Sumber.kt`, `data/SumberPemesananPalsu.kt`, `data/MuatAman.kt` |
| 3 | Muhammad Faza Prasetyo | 124140204 | **Bukti Berjalan** (`demoStatus` dengan `collect`, unit test pengujian `Flow` & `async`/`await`, serta dokumentasi keluaran konsol di `README.md`) | `data/DemoStatus.kt`, `App.kt`, `commonTest/.../SumberPemesananPalsuTest.kt`, `README.md` |

---

## Ringkasan Implementasi Modul 2

### 1. Model Data (`shared/src/commonMain/kotlin/id/ac/itera/pesanlapangan/model/`)
- **`Lapangan.kt`** *(Entitas Pendukung)*: `data class Lapangan(val id: Int, val nama: String, val jenisOlahraga: String, val hargaPerJam: Int)`
- **`Pemesanan.kt`** *(Entitas Utama)*: `data class Pemesanan` yang menyimpan data pemesanan lapangan olahraga dan memiliki satu kolom opsional (*nullable*) `val catatanTambahan: String? = null`.
- **`StatusPemesanan.kt`** *(Sealed Class Status)*:
  - `data object MenungguKonfirmasi : StatusPemesanan()`
  - `data class Dikonfirmasi(val petugas: String) : StatusPemesanan()`
  - `data class Selesai(val catatan: String) : StatusPemesanan()`
- **`PemesananExt.kt`** *(Extension Function & Null Safety)*:
  - `StatusPemesanan.label()` dan `StatusPemesanan.warna()` menggunakan `when` tanpa cabang `else`.
  - `Pemesanan.infoCatatan()` menggunakan operator *safe call* (`?.`) dan *Elvis* (`?:`).
  - `Pemesanan.ringkas()` menghasilkan ringkasan satu baris pemesanan lapangan.

### 2. Sumber Data Palsu (`shared/src/commonMain/kotlin/id/ac/itera/pesanlapangan/data/`)
- **`Sumber.kt`**: Kontrak generik `interface Sumber<T>` dengan `suspend fun ambil(id: Int): T` dan `fun pantau(id: Int): Flow<T>`.
- **`SumberPemesananPalsu.kt`**: Implementasi `Sumber<Pemesanan>` dengan simulasi waktu tunggu jaringan (`delay`) dan `Flow` yang memancarkan perubahan status secara bertahap (`MenungguKonfirmasi` → `Dikonfirmasi` → `Selesai`).
- **`MuatAman.kt`**: Fungsi `suspend fun muatAman(...)` yang menangani kegagalan pemuatan data dan melempar ulang `CancellationException` agar *structured concurrency* tetap terjaga.
- **`DemoStatus.kt`**: Fungsi `suspend fun demoStatus()` untuk membuka keran aliran `Flow` menggunakan `collect` dan mencetak perubahan status ke konsol.

---

## Contoh Keluaran Konsol

### 1. Perubahan Status dengan `Flow.collect` (`collectMencetakPerubahanStatus` / `demoStatus`)
```text
Sebelum collect
Menunggu petugas
Ditangani Pak Joko (Admin GOR)
Selesai: Lapangan selesai digunakan dengan tertib
Sesudah collect
```

### 2. Pengolahan Aliran `Flow` dengan Operator `filter`, `map`, `onEach`, `collect` (`operatorMenyaringDanMengubahStatus`)
```text
Log: Ditangani Pak Joko (Admin GOR)
Notifikasi: Ditangani Pak Joko (Admin GOR)
Log: Selesai: Lapangan selesai digunakan dengan tertib
Notifikasi: Selesai: Lapangan selesai digunakan dengan tertib
```

### 3. Eksekusi Bersamaan dengan `async` dan `await` (`asyncBersamaanSelesaiSekitar1000ms`)
```text
#7 Lapangan Futsal A GOR ITERA (Futsal) - Muhammad Faza Prasetyo, 2026-10-20 (19.00-21.00)
[Diajukan 18.10, Dikonfirmasi 18.40]
Waktu berjalan: 1000 ms
```

### 4. Pemeriksaan `data class copy()`, `sealed class`, dan *Null Safety* (`modelDataClassCopyDanNullSafety`)
```text
false
Menunggu petugas
Ditangani Pak Joko (Admin GOR)
0
```

### 5. Penanganan Error dengan `muatAman` (`muatAmanMenanganiError`)
```text
#7 Lapangan Futsal A GOR ITERA (Futsal) - Muhammad Faza Prasetyo, 2026-10-20 (19.00-21.00)
Gagal memuat pemesanan #0
```

---

## Cara Menjalankan Bukti Berjalan
Jalankan perintah berikut dari dalam direktori `Modul 2`:
```bash
./gradlew :shared:jvmTest --info
```
