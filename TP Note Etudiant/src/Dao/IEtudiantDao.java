package Dao;

public interface IEtudiantDao<T,ID> {
    T trouverParId(ID id);
}
