package com.tienda.controller;
        
import com.tiends.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService private categoriaService;
    
            
    @GetMapping("/listado")
    public String listado (Model model) {
        var productos = productoService.getProductos(activos: false);
        model.addAttribute(attributeName:"productos",attributeValue: productos)
        model.addAttribute(attributeName:"totalProductos",attributeValue: productos.size());
        var categorias = categoriaService.getCategorias(activos: false);
        model.addAttribute(attributeName:"categorias",attributeValue: categorias)
        return "/pruebas/listado";    
    }
    
    @GetMapping("/listado/idCategoria}")
    public String listado (Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(Categoria).getProductos();
        model.addAttribute(attributeName:"productos",attributeValue: productos)
        model.addAttribute(attributeName:"totalProductos",attributeValue: productos.size());
        var categorias = categoriaService.getCategorias(activos: false);
        model.addAttribute(attributeName:"categorias",attributeValue: categorias)
        return "/pruebas/listado";    
    }
    
    @GetMapping("/listado2")
    public String listado2 (Model model) {
        var productos = productoService.getProductos(activos: false);
        model.addAttribute(attributeName:"productos",attributeValue: productos)
        model.addAttribute(attributeName:"totalProductos",attributeValue: productos.size());
        return "/pruebas/listado";    
    }
    
    @PostMapping("/query1")
    public String consultaQuery1 (
            @RequestParam(value="precioInf") double precioInf,
            @RequestParam(value="precioSup") double precioSup,
            Model model)
        var productos = productoService.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup )
        model.addAttribute(attributeName:"productos",attributeValue: productos)
        model.addAttribute(attributeName:"totalProductos",attributeValue: productos.size());
        model.addAttribute(attributeName:"precioInf",attributeValue: precioInf)
        model.addAttribute(attributeName:"precioSup",attributeValue: precioSup)
        return "/pruebas/listado2";    
    }
    
    @PostMapping("/query2")
    public String consultaQuery2 (
            @RequestParam(value="precioInf") double precioInf,
            @RequestParam(value="precioSup") double precioSup,
            Model model)
        var productos = productoService.metodoJPQL(precioInf, precioSup )
        model.addAttribute(attributeName:"productos",attributeValue: productos)
        model.addAttribute(attributeName:"totalProductos",attributeValue: productos.size());
        model.addAttribute(attributeName:"precioInf",attributeValue: precioInf)
        model.addAttribute(attributeName:"precioSup",attributeValue: precioSup)
        return "/pruebas/listado2";    
    }
    
    @PostMapping("/query3")
    public String consultaQuery3 (
            @RequestParam(value="precioInf") double precioInf,
            @RequestParam(value="precioSup") double precioSup,
            Model model)
        var productos = productoService.metodoSQL(precioInf, precioSup )
        model.addAttribute(attributeName:"productos",attributeValue: productos)
        model.addAttribute(attributeName:"totalProductos",attributeValue: productos.size());
        model.addAttribute(attributeName:"precioInf",attributeValue: precioInf)
        model.addAttribute(attributeName:"precioSup",attributeValue: precioSup)
        return "/pruebas/listado2";    
    }
}
    


