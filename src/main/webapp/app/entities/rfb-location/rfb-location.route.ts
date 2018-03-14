import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { RfbLocationComponent } from './rfb-location.component';
import { RfbLocationDetailComponent } from './rfb-location-detail.component';
import { RfbLocationPopupComponent } from './rfb-location-dialog.component';
import { RfbLocationDeletePopupComponent } from './rfb-location-delete-dialog.component';

export const rfbLocationRoute: Routes = [
    {
        path: 'rfb-location',
        component: RfbLocationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'rfb-location/:id',
        component: RfbLocationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rfbLocationPopupRoute: Routes = [
    {
        path: 'rfb-location-new',
        component: RfbLocationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rfb-location/:id/edit',
        component: RfbLocationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rfb-location/:id/delete',
        component: RfbLocationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbLocations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
