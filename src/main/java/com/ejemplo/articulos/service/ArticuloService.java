
        package com.ejemplo.articulos.service;

        import com.ejemplo.articulos.model.Articulo;
        import java.util.List;
        import java.util.Optional;

        public interface ArticuloService {

    List<Articulo> listarArticulos();
    Optional<Articulo> obtenerArticuloPorId(Long id);

    List<Articulo> buscarPorNombre(String nombre);

    Articulo guardarArticulo(Articulo articulo);

    Articulo actualizarArticulo(Long id, Articulo articulo);

    Articulo actualizarPrecio(Long id, Double precio);
    Articulo actualizarStock(Long id, Integer stock);

    void eliminarArticulo(Long id);

}
