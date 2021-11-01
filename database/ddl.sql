CREATE TABLE TodosLists (
    id BIGSERIAL PRIMARY KEY
);
CREATE TABLE Todos (
    id BIGSERIAL,
    todos_list_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    done BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id, todos_list_id),
    CONSTRAINT todos_lists_fk1 FOREIGN KEY (todos_list_id) references TodosLists ON DELETE CASCADE
);