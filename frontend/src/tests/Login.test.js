import { render, screen } from '@testing-library/react';
import {Login} from '../views/Login';
import {BrowserRouter} from "react-router-dom";
import '@testing-library/jest-dom';

test('renders the Login page', () => {
    render(<BrowserRouter><Login /></BrowserRouter>);
    const loginText = screen.getByText('Login or Register');
    expect(loginText).toBeInTheDocument();
});
