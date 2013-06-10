package org.fao.geonet.kernel.domain;

/**
 * The list of reserverd groups
 * 
 * @author Jesse
 */
public enum ReservedGroup {
    all(1), intranet(0), guest(-1);

    // Not final so Tests can change id
    private int _id;

    private ReservedGroup(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public Group getGroupEntityTemplate() {
        return new Group().setId(_id).setName(name()).setDescription(name());
    }
}