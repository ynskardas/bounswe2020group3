from rest_framework import viewsets
from rest_framework import permissions
from api.models.profile import Profile
from api.permission import IsOwnerOrReadOnly
from api.serializers.profile import ProfileSerializer
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.decorators import action
from django.http import FileResponse
from rest_framework.parsers import MultiPartParser, FormParser
from rest_framework.response import Response
from rest_framework import status
import os


class ProfileViewSet(viewsets.ModelViewSet):
    """
    This viewset automatically provides `list`, `create`, `retrieve`,
    `update` and `destroy` actions.
    """
    parser_classes = (MultiPartParser, FormParser)
    queryset = Profile.objects.all()
    serializer_class = ProfileSerializer
    permission_classes = [permissions.IsAuthenticatedOrReadOnly,
                          IsOwnerOrReadOnly]
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['owner__id']

    @action(detail=True, methods=['get'])
    def retrieve_profile_picture(self, request, pk=None):
        picture = self.get_object().profile_picture
        return FileResponse(picture)

    @action(detail=True, methods=['delete'])
    def remove_profile_picture(self, request, pk=None):
        if self.get_object().profile_picture:
            self.get_object().profile_picture.delete()
            return Response("Profile picture is removed.", status.HTTP_200_OK)
        else:
            return Response("Profile picture is not found.",
                            status.HTTP_404_NOT_FOUND)

    def perform_create(self, serializer):
        serializer.save(owner=self.request.user)

    def update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', False)
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data,
                                         partial=partial)
        serializer.is_valid(raise_exception=True)

        if 'profile_picture' in request.data:
            if instance.profile_picture:
                if instance.profile_picture != request.data['profile_picture']:
                    if os.path.isfile(instance.profile_picture.path):
                        os.remove(instance.profile_picture.path)

        self.perform_update(serializer)
        return Response(serializer.data)
