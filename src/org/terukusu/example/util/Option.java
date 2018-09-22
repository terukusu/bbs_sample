package org.terukusu.example.util;

public class Option {
    private boolean hasValue;
    private boolean required;
    private String optionKey;

    protected Option(String optionKey, boolean hasValue, boolean required) {
        this.optionKey = optionKey;
        this.hasValue = hasValue;
        this.required = required;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Option [hasValue=" + hasValue + ", required=" + required + ", optionKey=" + optionKey + "]";
    }

    /**
     * @return the hasValue
     */
    public boolean hasValue() {
        return hasValue;
    }

    /**
     * @param hasValue
     *            the hasValue to set
     */
    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required
     *            the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the optionKey
     */
    public String getOptionKey() {
        return optionKey;
    }

    /**
     * @param optionKey
     *            the optionKey to set
     */
    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }
}
