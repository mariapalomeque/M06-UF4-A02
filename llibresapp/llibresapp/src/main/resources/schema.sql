CREATE TABLE IF NOT EXISTS libro (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editorial VARCHAR(255) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    tematica VARCHAR(255) NOT NULL,
    ISBN VARCHAR(255) NOT NULL
);
