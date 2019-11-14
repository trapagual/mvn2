/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.velsoft.mvn2.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author SGEN0290
 */
public class LazyPedidosDataModel extends LazyDataModel<Pedidos> {

    private static final long serialVersionUID = 6985529279841971648L;

    private List<Pedidos> datos;

    public LazyPedidosDataModel() {
    }

    public LazyPedidosDataModel(List<Pedidos> datos) {
        this.datos = datos;
    }

    @Override
    public Pedidos getRowData(String rowKey) {
        for (Pedidos p : datos) {
            if (p.getIdStr().equals(rowKey)) {
                return p;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Pedidos p) {
        return p.getId();
    }
    @Override
    public List<Pedidos> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Pedidos> data = new ArrayList<>();
 
        //filter
        for(Pedidos p : datos) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(p.getClass().getField(filterProperty).get(p));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(p);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
