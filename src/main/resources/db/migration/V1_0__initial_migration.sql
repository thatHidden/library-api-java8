CREATE TABLE IF NOT EXISTS books (
    isbn VARCHAR(13) PRIMARY KEY,
    title VARCHAR(50) UNIQUE NOT NULL,
    author VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS clients (
    id UUID PRIMARY KEY,
    full_name VARCHAR(60) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS borrows (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES clients(id),
    book_isbn VARCHAR(13) REFERENCES books(isbn),
    date DATE NOT NULL
);

INSERT INTO books (isbn, title, author) VALUES
    ('9780131103627', 'The C Programming Language', 'Brian Kernighan and Dennis Ritchie'),
    ('9780262033848', 'Introduction to Algorithms', 'Thomas H. Cormen et al.'),
    ('9780201616224', 'The Pragmatic Programmer', 'Andrew Hunt and David Thomas'),
    ('9780132350884', 'Clean Code', 'Robert C. Martin'),
    ('9781491950357', 'Designing Data-Intensive Applications', 'Martin Kleppmann');

INSERT INTO clients (id, full_name, birth_date) VALUES
    ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Alice Johnson', '1990-05-14'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d480', 'Bob Smith', '1985-11-20'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d481', 'Charlie Brown', '1992-03-08'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d482', 'Diana Prince', '1988-07-25'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d483', 'Evan Davis', '1995-01-30');

INSERT INTO borrows (id, user_id, book_isbn, date) VALUES
    ('f47ac10b-58cc-4372-a567-0e02b2c3d484', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', '9780131103627', '2024-11-01'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d485', 'f47ac10b-58cc-4372-a567-0e02b2c3d480', '9780262033848', '2024-11-02'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d486', 'f47ac10b-58cc-4372-a567-0e02b2c3d481', '9780201616224', '2024-11-03'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d487', 'f47ac10b-58cc-4372-a567-0e02b2c3d482', '9780132350884', '2024-11-04'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d488', 'f47ac10b-58cc-4372-a567-0e02b2c3d483', '9781491950357', '2024-11-05');


