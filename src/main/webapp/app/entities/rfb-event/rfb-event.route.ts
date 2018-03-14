import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { RfbEventComponent } from './rfb-event.component';
import { RfbEventDetailComponent } from './rfb-event-detail.component';
import { RfbEventPopupComponent } from './rfb-event-dialog.component';
import { RfbEventDeletePopupComponent } from './rfb-event-delete-dialog.component';

export const rfbEventRoute: Routes = [
    {
        path: 'rfb-event',
        component: RfbEventComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvents'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'rfb-event/:id',
        component: RfbEventDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvents'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rfbEventPopupRoute: Routes = [
    {
        path: 'rfb-event-new',
        component: RfbEventPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rfb-event/:id/edit',
        component: RfbEventPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rfb-event/:id/delete',
        component: RfbEventDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'RfbEvents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
