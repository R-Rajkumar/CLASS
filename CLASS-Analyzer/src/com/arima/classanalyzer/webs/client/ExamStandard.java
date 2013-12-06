package com.arima.classanalyzer.webs.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for examStandard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="examStandard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="general" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="generalCount" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="jaccardIndex" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sequenceAlignmentScore" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="termCount" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "examStandard", propOrder = {
    "general",
    "generalCount",
    "jaccardIndex",
    "sequenceAlignmentScore",
    "term",
    "termCount"
})
public class ExamStandard {

    protected String general;
    @XmlElement(nillable = true)
    protected List<Integer> generalCount;
    protected double jaccardIndex;
    protected double sequenceAlignmentScore;
    protected String term;
    @XmlElement(nillable = true)
    protected List<Integer> termCount;

    /**
     * Gets the value of the general property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneral() {
        return general;
    }

    /**
     * Sets the value of the general property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneral(String value) {
        this.general = value;
    }

    /**
     * Gets the value of the generalCount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generalCount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeneralCount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getGeneralCount() {
        if (generalCount == null) {
            generalCount = new ArrayList<Integer>();
        }
        return this.generalCount;
    }

    /**
     * Gets the value of the jaccardIndex property.
     * 
     */
    public double getJaccardIndex() {
        return jaccardIndex;
    }

    /**
     * Sets the value of the jaccardIndex property.
     * 
     */
    public void setJaccardIndex(double value) {
        this.jaccardIndex = value;
    }

    /**
     * Gets the value of the sequenceAlignmentScore property.
     * 
     */
    public double getSequenceAlignmentScore() {
        return sequenceAlignmentScore;
    }

    /**
     * Sets the value of the sequenceAlignmentScore property.
     * 
     */
    public void setSequenceAlignmentScore(double value) {
        this.sequenceAlignmentScore = value;
    }

    /**
     * Gets the value of the term property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerm(String value) {
        this.term = value;
    }

    /**
     * Gets the value of the termCount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the termCount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTermCount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getTermCount() {
        if (termCount == null) {
            termCount = new ArrayList<Integer>();
        }
        return this.termCount;
    }

}
