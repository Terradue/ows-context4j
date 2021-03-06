## Java library for OGC OWS Context

### owc4j library

The **owc4j** library provides the tools to manage OGC OWS Context documents.

### OGC OWS Context

The [OGC Web Services Context Document](http://www.opengeospatial.org/standards/owc) (OWS Context) describes the use cases, requirements and conceptual model for the OWS Context encoding standard. 

The OGC Web Services Context Document allows a set of configured information resources (service set) to be passed between applications primarily as a collection of services and supports in-line content as well (e.g. [Geography Markup Language (GML)](http://www.opengeospatial.org/standards/gml)). 

The goal is to support use cases such as the distribution of search results, the exchange of a set of resources such as OGC [Web Feature Service (WFS)](http://www.opengeospatial.org/standards/wfs), [Web Map Service (WMS)](http://www.opengeospatial.org/standards/wms), [Web Map Tile Service (WMTS)](http://www.opengeospatial.org/standards/wmts), [Web Coverage Service (WCS)](http://www.opengeospatial.org/standards/wcs) and others in a ‘common operating picture’. Additionally OWS Context can deliver a set of configured processing services ([Web Processing Service (WPS)](http://www.opengeospatial.org/standards/wps)) parameters to allow the processing to be reproduced on different nodes. 

The standard provides a core model, which is extended and encoded as defined in extensions to this standard. A ‘context document’ specifies a fully configured service set which can be exchanged (with a consistent interpretation) among clients supporting the standard. 

**Find out more about OGC OWS Context:**

* [OGC® OWS Context Conceptual Model](https://portal.opengeospatial.org/files/?artifact_id=55182)
* [OGC® OWS Context Atom Encoding Standard](https://portal.opengeospatial.org/files/?artifact_id=55183)
* [OGC® OWS-10 Rules for JSON and GeoJSON Adoption: Focus on OWS-Context](https://portal.opengeospatial.org/files/?artifact_id=57477)

**There's an OWS Context demonstration live at [terradue.github.io/ows-context-demo/](http://terradue.github.io/ows-context-demo)**

### Getting Started

The folder https://github.com/Terradue/ows-context4j/tree/master/src/test/java/com/terradue/owc/test contains several tests that represent examples of the owc4j library.

### Limitations

The library does not provide yet the full coverage of the OGC OWS Context specification. 

### Questions, bugs, and suggestions

Please file any bugs or questions as [issues](https://github.com/Terradue/ows-context4j/issues/new) 

### Want to contribute?

Fork the repository [here](https://github.com/Terradue/ows-context4j/fork) and send us pull requests.
