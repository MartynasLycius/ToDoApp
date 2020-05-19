/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.constant;

import com.vaadin.icons.VaadinIcons;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A value object class works as a resource provider (e.g. icons, colors) for the categories
 * {@see com.nazmul.tod.domain.Category}.
 * @author nazmul
 */
public enum CategoryType {
    All(0l, "all", VaadinIcons.ELLIPSIS_CIRCLE_O, "rgba(0, 255, 255, 1)"),
    WORK(1l, "work", VaadinIcons.BRIEFCASE, "rgba(0, 0, 255, 1)"),
    HOME(2l, "home", VaadinIcons.HOME, "rgba(143, 188, 143, 1)"),
    PERSONAL(3l, "personal", VaadinIcons.FAMILY, "rgba(245, 255, 250, 1)");

    private Long id;
    private String key;
    private VaadinIcons icon;
    private String rgba;

    private CategoryType(Long id, String key, VaadinIcons icon, String rgba) {
        this.id = id;
        this.key = key;
        this.icon = icon;
        this.rgba = rgba;
    }

    public static CategoryType getWORK() {
        return WORK;
    }

    public static CategoryType getHOME() {
        return HOME;
    }

    public static CategoryType getPERSONAL() {
        return PERSONAL;
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public VaadinIcons getIcon() {
        return icon;
    }

    public String getColor() {
        return rgba;
    }
    
    public static CategoryType findById(Long id) {
        return Stream.of(CategoryType.values())
                .filter((t) -> Objects.equals(id, t.getId()))
                .findFirst()
                .get();
    }

    public static List<Long> allowedCategoryIds(CategoryType type) {
        List<Long> allowedIds = Objects.equals(All, type) ? Arrays.asList(WORK.id, HOME.id, PERSONAL.id)
                : Arrays.asList(type.id);
        
        return allowedIds;
    }
    
    /**
     * Gets colors of corresponding Category type by the given names.
     * @param enumNames Names of the enum (e.g. 'WORK') in string
     * @return returns array of String or color property of the corresponding enum
     * @throws NullPointerException if null is given as the enum names
     */
    public static String[] getCategoryTypeColorByEnumNames(String ... enumNames) {
        String[] bgColorByCategoryType = Stream.of(enumNames)
                .map((name) -> CategoryType.valueOf(name.toUpperCase()).getColor())
                .toArray(String[]::new);
        return bgColorByCategoryType;
    }
}
