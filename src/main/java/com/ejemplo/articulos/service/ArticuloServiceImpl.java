package com.ejemplo.articulos.service;

import com.ejemplo.articulos.model.Articulo;
import com.ejemplo.articulos.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public List<Articulo> listarArticulos() {
        return articuloRepository.findAll();
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorId(Long id) {
        return articuloRepository.findById(id);
    }

    @Override
    public List<Articulo> buscarPorNombre(String nombre) {
        return articuloRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Articulo guardarArticulo(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo actualizarArticulo(Long id, Articulo articulo) {
        Articulo existente = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        existente.setNombre(articulo.getNombre());
        existente.setDescripcion(articulo.getDescripcion());
        existente.setPrecio(articulo.getPrecio());
        existente.setCategoria(articulo.getCategoria());
        existente.setImagen(articulo.getImagen());
        existente.setStock(articulo.getStock());

        return articuloRepository.save(existente);
    }

    @Override
    public Articulo actualizarPrecio(Long id, Double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }

        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        articulo.setPrecio(precio);
        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo actualizarStock(Long id, Integer stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        articulo.setStock(stock);
        return articuloRepository.save(articulo);
    }

    @Override
    public void eliminarArticulo(Long id) {
        if (!articuloRepository.existsById(id)) {
            throw new RuntimeException("El artículo no existe");
        }
        articuloRepository.deleteById(id);
    }

}
