import React from "react";
import {
  Container,
  Row,
  FooterLink,
  Heading,
  StyledCopy,
} from "./FooterStyles";

const Footer = () => {
  return (
    <>
      <Container>
        <Row>
          <Heading>USEFUL INFO</Heading>
          <FooterLink href="#">Privacy Policy</FooterLink>
          <FooterLink href="#">Term &amp; Conditions</FooterLink>
          <FooterLink href="#">Cookie Policy</FooterLink>
          <FooterLink href="#">FAQs</FooterLink>
        </Row>
        <Row>
          <Heading>YOU CAN FIND US IN</Heading>
          <FooterLink href="#">London, UK</FooterLink>
          <FooterLink href="#">Bucharest, RO</FooterLink>
          <FooterLink href="#">Paris, FR</FooterLink>
          <FooterLink href="#">Sofia, BG</FooterLink>
        </Row>
        <Row>
          <Heading>ABOUT GELATO &amp; DONUTS</Heading>
          <FooterLink href="#">About us</FooterLink>
          <FooterLink href="#">Store locator</FooterLink>
          <FooterLink href="#">Franchise</FooterLink>
          <FooterLink href="#">Careers</FooterLink>
          <FooterLink href="#">Contact us</FooterLink>
        </Row>
        <Row>
          <Heading>NEWSLETTER</Heading>
          <FooterLink href="#">
            <form>
              <label>
                <input type="text" name="newsletter" />
              </label>

              <button>Subscribe</button>
            </form>
          </FooterLink>
        </Row>
      </Container>
      <StyledCopy> &#169; 2021 Gelato &amp; Donuts;</StyledCopy>
    </>
  );
};
export default Footer;
