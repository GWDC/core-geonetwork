//=============================================================================
//===	Copyright (C) 2001-2007 Food and Agriculture Organization of the
//===	United Nations (FAO-UN), United Nations World Food Programme (WFP)
//===	and United Nations Environment Programme (UNEP)
//===
//===	This program is free software; you can redistribute it and/or modify
//===	it under the terms of the GNU General Public License as published by
//===	the Free Software Foundation; either version 2 of the License, or (at
//===	your option) any later version.
//===
//===	This program is distributed in the hope that it will be useful, but
//===	WITHOUT ANY WARRANTY; without even the implied warranty of
//===	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
//===	General Public License for more details.
//===
//===	You should have received a copy of the GNU General Public License
//===	along with this program; if not, write to the Free Software
//===	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
//===
//===	Contact: Jeroen Ticheler - FAO - Viale delle Terme di Caracalla 2,
//===	Rome - Italy. email: geonetwork@osgeo.org
//==============================================================================

package org.fao.geonet.services.metadata;

import jeeves.constants.Jeeves;
import jeeves.interfaces.Service;
import jeeves.server.ServiceConfig;
import jeeves.server.context.ServiceContext;
import org.fao.geonet.constants.Geonet;
import org.fao.geonet.repository.MetadataCategoryRepository;
import org.jdom.Element;

import java.util.List;

//=============================================================================

/** Returns all categories.
  */

public class PrepareBatchUpdateCategories implements Service
{
	//--------------------------------------------------------------------------
	//---
	//--- Init
	//---
	//--------------------------------------------------------------------------

	public void init(String appPath, ServiceConfig params) throws Exception {}

	//--------------------------------------------------------------------------
	//---
	//--- Service
	//---
	//--------------------------------------------------------------------------

	public Element exec(Element params, ServiceContext context) throws Exception
	{
		Element isOwner = new Element("owner").setText("true");
		
		//--- retrieve categories
		Element elCateg = context.getBean(MetadataCategoryRepository.class).findAllAsXml();
		@SuppressWarnings("unchecked")
        List<Element> list = elCateg.getChildren();

		for (Element element : list) {
			element.setName(Geonet.Elem.CATEGORY);
		}

		//-----------------------------------------------------------------------
		//--- put all together

		Element elRes = new Element(Jeeves.Elem.RESPONSE)
										.addContent(elCateg)
										.addContent(isOwner);

		return elRes;
	}
}

//=============================================================================

