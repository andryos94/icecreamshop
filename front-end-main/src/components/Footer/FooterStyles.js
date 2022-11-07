import styled from "styled-components/macro";

export const Container = styled.div`
  display: flex;
  justify-content: space-between;
  background: #343a40;
  width: 100%;
  & > {
    flex: 1;
  }
  @media (max-width: 1000px) {
    display: flex;
    flex-direction: column;
    margin-left: opx;
  }

  padding: 50px 10px 40px;
  @media (max-width: 1000px) {
    padding: 70px 30px;
  }
`;

export const Row = styled.div`
  display: flex;
  flex-direction: column;
  width: 320px;

  @media (max-width: 400px) {
    display: flex;
    flex-direction: columns;
  }
`;

export const Form = styled.form`
  display: flex;
`;

export const FooterLink = styled.div`
  color: #fff;
  margin-bottom: 20px;
  font-size: 18px;
  text-decoration: none;

  &:hover {
    color: blue;
    transition: 200ms ease-in;
  }
`;

export const Heading = styled.p`
  font-size: 24px;
  color: #fff;
  margin-bottom: 40px;
  font-weight: bold;
  text-decoration: underline;
`;

export const StyledCopy = styled.div`
  padding: 16px 0;
  width: 100%;
  text-align: center;
`;
