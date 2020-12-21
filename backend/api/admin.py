from django.contrib import admin

# Register your models here.
from .models.following import Following, FollowRequest
from .models.profile import Profile
from .models.project import Project
from .models.event import Event
from .models.file import File
from .models.notification import Notification

admin.site.register(Profile)
admin.site.register(Project)
admin.site.register(Event)
admin.site.register(File)
admin.site.register(Following)
admin.site.register(FollowRequest)
admin.site.register(Notification)
