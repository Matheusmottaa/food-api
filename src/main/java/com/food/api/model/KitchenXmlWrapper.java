package com.food.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.food.api.domain.model.Kitchen;

import lombok.Data;
import lombok.NonNull;

@Data
@JacksonXmlRootElement(localName = "kitchens")
public class KitchenXmlWrapper {

	@JacksonXmlProperty(localName = "kitchen")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull
	private List<Kitchen> cooks;
}
