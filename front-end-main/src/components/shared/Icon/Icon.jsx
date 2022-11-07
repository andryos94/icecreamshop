import React from "react";
import PropTypes from "prop-types";

import { StyledIconContainer, StyledIconLabel } from "./Icon.style";

const Icon = ({ icon, label }) => (
  <StyledIconContainer>
    {icon}
    {label && <StyledIconLabel>{label}</StyledIconLabel>}
  </StyledIconContainer>
);

Icon.propTypes = {
  icon: PropTypes.element.isRequired,
  label: PropTypes.string,
};

Icon.defaultProps = {
  label: "",
};

export default Icon;
