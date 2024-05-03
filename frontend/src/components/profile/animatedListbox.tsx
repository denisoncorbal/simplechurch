import { MenuListboxSlotProps } from '@mui/base/Menu';
import { CssTransition } from '@mui/base/Transitions';
import { PopupContext } from '@mui/base/Unstable_Popup';
import { styled } from '@mui/system';
import React from 'react';
const AnimatedListbox = React.forwardRef(function AnimatedListbox(
    props: MenuListboxSlotProps,
    ref: React.ForwardedRef<HTMLUListElement>,
) {
    const { ownerState, ...other } = props;
    const popupContext = React.useContext(PopupContext);

    if (popupContext == null) {
        throw new Error(
            'The `AnimatedListbox` component cannot be rendered outside a `Popup` component',
        );
    }

    const verticalPlacement = popupContext.placement.split('-')[0];

    return (
        <CssTransition
            className={`placement-${verticalPlacement}`}
            enterClassName="open"
            exitClassName="closed"
        >
            <Listbox {...other} ref={ref} />
        </CssTransition>
    );
});

const Listbox = styled('ul')(
    ({ theme }) => `
  font-family: 'IBM Plex Sans', sans-serif;
  font-size: 0.875rem;
  box-sizing: border-box;
  padding: 6px;
  margin: 12px 0;
  min-width: 200px;
  border-radius: 12px;
  overflow: auto;
  outline: 0px;
  background: ${theme.palette.mode === 'dark' ? theme.palette.grey['900'] : '#fff'};
  border: 1px solid ${theme.palette.mode === 'dark' ? theme.palette.grey['700'] : theme.palette.grey['200']};
  color: ${theme.palette.mode === 'dark' ? theme.palette.grey['300'] : theme.palette.grey['900']};
  box-shadow: 0px 4px 30px ${theme.palette.mode === 'dark' ? theme.palette.grey['900'] : theme.palette.grey['200']};
  z-index: 1;

  .closed & {
    opacity: 0;
    transform: scale(0.95, 0.8);
    transition: opacity 200ms ease-in, transform 200ms ease-in;
  }
  
  .open & {
    opacity: 1;
    transform: scale(1, 1);
    transition: opacity 100ms ease-out, transform 100ms cubic-bezier(0.43, 0.29, 0.37, 1.48);
  }

  .placement-top & {
    transform-origin: bottom;
  }

  .placement-bottom & {
    transform-origin: top;
  }
  `,
);

export default AnimatedListbox;