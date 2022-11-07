import styled from 'styled-components/macro';

export const StyledIconContainer = styled.div`
  width: min-content;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: ${(props) => props.theme.palette.dark.main};

  :hover {
    color: ${(props) => props.theme.palette.primary.main};
  }

  :active {
    color: ${(props) => props.theme.palette.primary.hover};
  }

  > svg {
    fill: currentColor;
  }
`;

export const StyledIconLabel = styled.div`
  user-select: none;
  margin-top: 5px;
`;
